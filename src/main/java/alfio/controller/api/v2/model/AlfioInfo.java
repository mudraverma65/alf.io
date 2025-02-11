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
package alfio.controller.api.v2.model;


import lombok.Getter;

@Getter
public class AlfioInfo {

    private final boolean demoModeEnabled;
    private final boolean devModeEnabled;
    private final boolean prodModeEnabled;
    private final AnalyticsConfiguration analyticsConfiguration;
    private final String globalPrivacyPolicyUrl;
    private final String globalTermsUrl;
    private final InvoicingConfiguration invoicingConfiguration;
    private final String announcementBannerContentHTML;

    public AlfioInfo(boolean demoModeEnabled,
                     boolean devModeEnabled,
                     boolean prodModeEnabled,
                     AnalyticsConfiguration analyticsConfiguration,
                     String globalPrivacyPolicyUrl,
                     String globalTermsUrl,
                     InvoicingConfiguration invoicingConfiguration,
                     String announcementBannerContentHTML) {
        this.demoModeEnabled = demoModeEnabled;
        this.devModeEnabled = devModeEnabled;
        this.prodModeEnabled = prodModeEnabled;
        this.analyticsConfiguration = analyticsConfiguration;
        this.globalPrivacyPolicyUrl = globalPrivacyPolicyUrl;
        this.globalTermsUrl = globalTermsUrl;
        this.invoicingConfiguration = invoicingConfiguration;
        this.announcementBannerContentHTML = announcementBannerContentHTML;
    }
}
