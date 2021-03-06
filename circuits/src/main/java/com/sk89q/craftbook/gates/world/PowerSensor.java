package com.sk89q.craftbook.gates.world;

import org.bukkit.Server;
import org.bukkit.block.Block;

import com.sk89q.craftbook.ChangedSign;
import com.sk89q.craftbook.ic.AbstractIC;
import com.sk89q.craftbook.ic.AbstractICFactory;
import com.sk89q.craftbook.ic.ChipState;
import com.sk89q.craftbook.ic.IC;
import com.sk89q.craftbook.ic.ICFactory;
import com.sk89q.craftbook.ic.ICUtil;
import com.sk89q.craftbook.ic.ICVerificationException;
import com.sk89q.craftbook.ic.RestrictedIC;

/**
 * @author Silthus
 */
public class PowerSensor extends AbstractIC {


    private Block center;

    public PowerSensor(Server server, ChangedSign block, ICFactory factory) {

        super(server, block, factory);
        load();
    }

    private void load() {

        try {
            center = ICUtil.parseBlockLocation(getSign());
        } catch (Exception ignored) {
        }
    }

    @Override
    public String getTitle() {

        return "Power Sensor";
    }

    @Override
    public String getSignTitle() {

        return "POWER SENSOR";
    }

    @Override
    public void trigger(ChipState chip) {

        if (chip.getInput(0)) {
            chip.setOutput(0, isPowered());
        }
    }

    protected boolean isPowered() {

        return center.isBlockPowered() || center.isBlockIndirectlyPowered();
    }

    public static class Factory extends AbstractICFactory implements RestrictedIC {

        public Factory(Server server) {

            super(server);
        }

        @Override
        public IC create(ChangedSign sign) {

            return new PowerSensor(getServer(), sign, this);
        }

        @Override
        public void verify(ChangedSign sign) throws ICVerificationException {

            ICUtil.verifySignSyntax(sign);
        }
    }
}
