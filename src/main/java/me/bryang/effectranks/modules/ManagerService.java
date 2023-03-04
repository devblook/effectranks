package me.bryang.effectranks.modules;

import me.bryang.effectranks.manager.GroupManager;
import me.bryang.effectranks.services.Service;

import javax.inject.Inject;

public class ManagerService implements Service {

    @Inject
    private GroupManager groupManager;

    @Override
    public void init() {
        groupManager.init();
    }
}
