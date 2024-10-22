package com.patik.patikliResource.SelfRefresher;

import dev.aurelium.auraskills.api.AuraSkillsApi;
import dev.aurelium.auraskills.api.user.SkillsUser;
import org.bukkit.event.Listener;

public class SkilledBreaking implements Listener {
    AuraSkillsApi auraSkills = AuraSkillsApi.get();

    SkillsUser user = auraSkills.getUser(player.getUniqueId());
}
