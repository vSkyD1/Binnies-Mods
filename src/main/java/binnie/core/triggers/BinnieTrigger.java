package binnie.core.triggers;

import binnie.Binnie;
import binnie.core.AbstractMod;
import binnie.core.BinnieCore;
import binnie.core.resource.BinnieIcon;
import buildcraft.api.statements.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

final class BinnieTrigger implements ITriggerExternal {
    protected static BinnieTrigger triggerNoBlankTemplate;
    protected static BinnieTrigger triggerNoTemplate;
    protected static BinnieTrigger triggerIsWorking;
    protected static BinnieTrigger triggerIsNotWorking;
    protected static BinnieTrigger triggerCanWork;
    protected static BinnieTrigger triggerCannotWork;
    protected static BinnieTrigger triggerPowerNone;
    protected static BinnieTrigger triggerPowerLow;
    protected static BinnieTrigger triggerPowerMedium;
    protected static BinnieTrigger triggerPowerHigh;
    protected static BinnieTrigger triggerPowerFull;
    protected static BinnieTrigger triggerSerumFull;
    protected static BinnieTrigger triggerSerumPure;
    protected static BinnieTrigger triggerSerumEmpty;
    protected static BinnieTrigger triggerAcclimatiserNone;
    protected static BinnieTrigger triggerAcclimatiserHot;
    protected static BinnieTrigger triggerAcclimatiserCold;
    protected static BinnieTrigger triggerAcclimatiserWet;
    protected static BinnieTrigger triggerAcclimatiserDry;
    private static int incrementalID;

    static {
        BinnieTrigger.incrementalID = 800;
    }

    private String desc;
    private String tag;
    private BinnieIcon icon;
    private int id;

    public BinnieTrigger(final String desc, final String tag, final String iconFile) {
        this(desc, tag, BinnieCore.instance, iconFile);
    }

    public BinnieTrigger(final String desc, final String tag, final AbstractMod mod, final String iconFile) {
        this.id = 0;
        this.id = BinnieTrigger.incrementalID++;
        this.tag = tag;
        StatementManager.registerStatement(this);
        TriggerProvider.triggers.add(this);
        this.icon = Binnie.Resource.getItemIcon(mod, iconFile);
        this.desc = desc;
    }

    public String getDescription() {
        return this.desc;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final IIconRegister register) {
        return this.icon.getIcon(register);
    }

    public String getUniqueTag() {
        return this.tag;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon() {
        return this.icon.getIcon();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister iconRegister) {
        this.icon.registerIcon(iconRegister);
    }

    public int maxParameters() {
        return 0;
    }

    public int minParameters() {
        return 0;
    }

    public IStatementParameter createParameter(final int index) {
        return null;
    }

    public IStatement rotateLeft() {
        return null;
    }

    public boolean isTriggerActive(final TileEntity target, final ForgeDirection side, final IStatementContainer source, final IStatementParameter[] parameters) {
        return false;
    }
}
