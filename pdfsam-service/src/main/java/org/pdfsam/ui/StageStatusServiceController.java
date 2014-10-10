/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 07/ott/2014
 * Copyright 2013-2014 by Andrea Vacondio (andrea.vacondio@gmail.com).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pdfsam.ui;

import static org.sejda.eventstudio.StaticStudio.eventStudio;

import javax.inject.Inject;
import javax.inject.Named;

import org.pdfsam.module.ClearUsageController;
import org.sejda.eventstudio.annotation.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller for the {@link StageStatusService}
 * 
 * @author Andrea Vacondio
 *
 */
@Named
public class StageStatusServiceController {
    private static final Logger LOG = LoggerFactory.getLogger(ClearUsageController.class);

    private StageStatusService service;

    @Inject
    public StageStatusServiceController(StageStatusService service) {
        this.service = service;
        eventStudio().addAnnotatedListeners(this);
    }

    /**
     * Request to clear the latest stage status
     * 
     * @param event
     */
    @EventListener
    public void requestClear(ClearLatestStageStatusRequest event) {
        LOG.debug("Clearing latest stage status");
        service.clearStageStatus();
    }

    @EventListener
    public void requestStageStatus(SetLatestStageStatusRequest event) {
        LOG.debug("Setting latest statge status to: {}", event.getStatus());
        service.save(event.getStatus());
    }

}