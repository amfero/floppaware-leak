package floppaware.client.modules;
import floppaware.api.module.Module;
import floppaware.api.module.*;
import floppaware.api.setting.*;

public class Surround extends Module {

    CheckBoxSetting rotate = new CheckBoxSetting("Rotate", this, false);
    CheckBoxSetting autotoggle = new CheckBoxSetting("Auto Toggle", this, false);

    public Surround() {
        super("Surround", "AutoFeetPlace", -1, Category.Combat, true);
        addSetting(rotate);
        addSetting(autotoggle);
    }

}