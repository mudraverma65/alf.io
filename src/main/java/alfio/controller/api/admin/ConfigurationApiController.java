/**
 * This file is part of alf.io.
 *
 * alf.io is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * alf.io is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with alf.io.  If not, see <http://www.gnu.org/licenses/>.
 */
package alfio.controller.api.admin;

import alfio.controller.api.support.TicketHelper;
import alfio.job.executor.AssignTicketToSubscriberJobExecutor;
import alfio.manager.BillingDocumentManager;
import alfio.manager.EventManager;
import alfio.manager.system.AdminJobExecutor;
import alfio.manager.system.AdminJobManager;
import alfio.manager.system.ConfigurationLevel;
import alfio.manager.system.ConfigurationManager;
import alfio.manager.user.UserManager;
import alfio.model.EventAndOrganizationId;
import alfio.model.modification.ConfigurationModification;
import alfio.model.system.Configuration;
import alfio.model.system.ConfigurationKeys;
import alfio.util.ClockProvider;
import alfio.util.RequestUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static alfio.manager.system.AdminJobExecutor.JobName.ASSIGN_TICKETS_TO_SUBSCRIBERS;
import static alfio.model.system.ConfigurationKeys.*;
import static alfio.util.Wrappers.optionally;
import static java.util.Objects.requireNonNullElse;

@RestController
@RequestMapping("/admin/api/configuration")
public class ConfigurationApiController {

    private final ConfigurationManager configurationManager;
    private final BillingDocumentManager billingDocumentManager;
    private final AdminJobManager adminJobManager;
    private final EventManager eventManager;
    private final ClockProvider clockProvider;
    private final UserManager userManager;

    public ConfigurationApiController(ConfigurationManager configurationManager,
                                      BillingDocumentManager billingDocumentManager,
                                      AdminJobManager adminJobManager,
                                      EventManager eventManager,
                                      ClockProvider clockProvider,
                                      UserManager userManager) {
        this.configurationManager = configurationManager;
        this.billingDocumentManager = billingDocumentManager;
        this.adminJobManager = adminJobManager;
        this.eventManager = eventManager;
        this.clockProvider = clockProvider;
        this.userManager = userManager;
    }

    @GetMapping(value = "/load")
    public Map<ConfigurationKeys.SettingCategory, List<Configuration>> loadConfiguration(Principal principal) {
        return configurationManager.loadAllSystemConfigurationIncludingMissing(principal.getName());
    }

    @GetMapping("/basic-configuration-needed")
    public boolean isBasicConfigurationNeeded() {
        return configurationManager.isBasicConfigurationNeeded();
    }

    @PostMapping(value = "/update")
    public boolean updateConfiguration(@RequestBody ConfigurationModification configuration) {
        configurationManager.saveSystemConfiguration(ConfigurationKeys.fromString(configuration.getKey()), configuration.getValue());
        return true;
    }

    @PostMapping(value = "/update-bulk")
    public boolean updateConfiguration(@RequestBody Map<ConfigurationKeys.SettingCategory, List<ConfigurationModification>> input) {
        List<ConfigurationModification> list = Objects.requireNonNull(input).values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        configurationManager.saveAllSystemConfiguration(list);
        return true;
    }

    @GetMapping(value = "/organizations/{organizationId}/load")
    public Map<ConfigurationKeys.SettingCategory, List<Configuration>> loadOrganizationConfiguration(@PathVariable("organizationId") int organizationId, Principal principal) {
        return configurationManager.loadOrganizationConfig(organizationId, principal.getName());
    }

    @PostMapping(value = "/organizations/{organizationId}/update")
    public boolean updateOrganizationConfiguration(@PathVariable("organizationId") int organizationId,
                                                                     @RequestBody Map<ConfigurationKeys.SettingCategory, List<ConfigurationModification>> input, Principal principal) {
        configurationManager.saveAllOrganizationConfiguration(organizationId, input.values().stream().flatMap(Collection::stream).collect(Collectors.toList()), principal.getName());
        return true;
    }

    @GetMapping(value = "/events/{eventId}/load")
    public Map<ConfigurationKeys.SettingCategory, List<Configuration>> loadEventConfiguration(@PathVariable("eventId") int eventId,
                                                                                              Principal principal) {
        return configurationManager.loadEventConfig(eventId, principal.getName());
    }

    @GetMapping("/events/{eventName}/single/{key}")
    public ResponseEntity<String> getSingleConfigForEvent(@PathVariable("eventName") String eventShortName,
                                                         @PathVariable("key") String key,
                                                         Principal principal) {

        var optionalEvent = eventManager.getOptionalByName(eventShortName, principal.getName());

        if(optionalEvent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var event = optionalEvent.get();
        String singleConfigForEvent = configurationManager.getSingleConfigForEvent(event.getId(), key, principal.getName());
        if(singleConfigForEvent == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(singleConfigForEvent);
    }

    @GetMapping("/organizations/{organizationId}/single/{key}")
    public ResponseEntity<String> getSingleConfigForOrganization(@PathVariable("organizationId") int organizationId,
                                                                 @PathVariable("key") String key,
                                                                 Principal principal) {

        String config = configurationManager.getSingleConfigForOrganization(organizationId, key, principal.getName());
        if(config == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(config);
    }

    @PostMapping(value = "/organizations/{organizationId}/events/{eventId}/update")
    public boolean updateEventConfiguration(@PathVariable("organizationId") int organizationId, @PathVariable("eventId") int eventId,
                                                    @RequestBody Map<ConfigurationKeys.SettingCategory, List<ConfigurationModification>> input, Principal principal) {
        configurationManager.saveAllEventConfiguration(eventId, organizationId, input.values().stream().flatMap(Collection::stream).collect(Collectors.toList()), principal.getName());
        return true;
    }

    @PostMapping(value = "/events/{eventId}/categories/{categoryId}/update")
    public boolean updateCategoryConfiguration(@PathVariable("categoryId") int categoryId, @PathVariable("eventId") int eventId,
                                                    @RequestBody Map<ConfigurationKeys.SettingCategory, List<ConfigurationModification>> input, Principal principal) {
        configurationManager.saveCategoryConfiguration(categoryId, eventId, input.values().stream().flatMap(Collection::stream).collect(Collectors.toList()), principal.getName());
        return true;
    }

    @GetMapping(value = "/events/{eventId}/categories/{categoryId}/load")
    public Map<ConfigurationKeys.SettingCategory, List<Configuration>> loadCategoryConfiguration(@PathVariable("eventId") int eventId, @PathVariable("categoryId") int categoryId, Principal principal) {
        return configurationManager.loadCategoryConfig(eventId, categoryId, principal.getName());
    }

    @DeleteMapping(value = "/organization/{organizationId}/key/{key}")
    public boolean deleteOrganizationLevelKey(@PathVariable("organizationId") int organizationId, @PathVariable("key") ConfigurationKeys key, Principal principal) {
        configurationManager.deleteOrganizationLevelByKey(key.getValue(), organizationId, principal.getName());
        return true;
    }

    @DeleteMapping(value = "/event/{eventId}/key/{key}")
    public boolean deleteEventLevelKey(@PathVariable("eventId") int eventId, @PathVariable("key") ConfigurationKeys key, Principal principal) {
        configurationManager.deleteEventLevelByKey(key.getValue(), eventId, principal.getName());
        return true;
    }

    @DeleteMapping(value = "/event/{eventId}/category/{categoryId}/key/{key}")
    public boolean deleteCategoryLevelKey(@PathVariable("eventId") int eventId, @PathVariable("categoryId") int categoryId, @PathVariable("key") ConfigurationKeys key, Principal principal) {
        configurationManager.deleteCategoryLevelByKey(key.getValue(), eventId, categoryId, principal.getName());
        return true;
    }

    @DeleteMapping(value = "/key/{key}")
    public boolean deleteKey(@PathVariable("key") String key) {
        configurationManager.deleteKey(key);
        return true;
    }

    @GetMapping(value = "/eu-countries")
    public List<Pair<String, String>> loadEUCountries() {
        return TicketHelper.getLocalizedEUCountriesForVat(Locale.ENGLISH, configurationManager.getForSystem(ConfigurationKeys.EU_COUNTRIES_LIST).getRequiredValue());
    }

    @GetMapping(value = "/instance-settings")
    public InstanceSettings loadInstanceSettings() {
        var settings = configurationManager.getFor(EnumSet.of(DESCRIPTION_MAXLENGTH, BASE_URL), ConfigurationLevel.system());
        return new InstanceSettings(settings.get(DESCRIPTION_MAXLENGTH).getValueAsIntOrDefault(4096), settings.get(BASE_URL).getRequiredValue());
    }

    @GetMapping(value = "/platform-mode/status/{organizationId}")
    public Map<String, Boolean> loadPlatformModeStatus(@PathVariable("organizationId") int organizationId) {
        Map<String, Boolean> result = new HashMap<>();
        boolean platformModeEnabled = configurationManager.getForSystem(PLATFORM_MODE_ENABLED).getValueAsBooleanOrDefault();
        result.put("enabled", platformModeEnabled);
        if(platformModeEnabled) {
            var options = configurationManager.getFor(List.of(STRIPE_CONNECTED_ID, MOLLIE_CONNECT_REFRESH_TOKEN), ConfigurationLevel.organization(organizationId));
            result.put("stripeConnected", options.get(STRIPE_CONNECTED_ID).isPresent());
            result.put("mollieConnected", options.get(MOLLIE_CONNECT_REFRESH_TOKEN).isPresent());
        }
        return result;
    }

    @GetMapping("/setting-categories")
    public Collection<ConfigurationKeys.SettingCategory> getSettingCategories() {
        return EnumSet.allOf(ConfigurationKeys.SettingCategory.class);
    }

    @PutMapping("/generate-tickets-for-subscriptions")
    public ResponseEntity<Boolean> generateTicketsForSubscriptions(@RequestParam(value = "eventId", required = false) Integer eventId,
                                                                   @RequestParam(value = "organizationId", required = false) Integer organizationId,
                                                                   Principal principal) {
        boolean admin = RequestUtils.isAdmin(principal);
        Map<String, Object> jobMetadata = null;

        if(!admin && (organizationId == null || !userManager.isOwnerOfOrganization(principal.getName(), organizationId))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (eventId != null && organizationId != null) {
            eventManager.checkOwnership(new EventAndOrganizationId(eventId, organizationId), principal.getName(), organizationId);
            jobMetadata = Map.of(AssignTicketToSubscriberJobExecutor.EVENT_ID, eventId, AssignTicketToSubscriberJobExecutor.ORGANIZATION_ID, organizationId);
        } else if(organizationId != null) {
            jobMetadata = Map.of(AssignTicketToSubscriberJobExecutor.ORGANIZATION_ID, organizationId);
        }

        return ResponseEntity.ok(adminJobManager.scheduleExecution(ASSIGN_TICKETS_TO_SUBSCRIBERS, requireNonNullElse(jobMetadata, Map.of())));
    }

    record InstanceSettings(int descriptionMaxLength, String baseUrl) {
    }
}
