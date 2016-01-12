package binnie.genetics.core;

import binnie.core.BinnieCore;
import binnie.core.network.IPacketID;
import binnie.core.network.packet.MessageBinnie;
import binnie.core.network.packet.MessageNBT;
import binnie.genetics.genetics.GeneTracker;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;

public enum GeneticsPacket implements IPacketID {
    GeneTrackerSync;

    @Override
    public void onMessage(final MessageBinnie message, final MessageContext context) {
        if (this == GeneticsPacket.GeneTrackerSync && context.side == Side.CLIENT) {
            final MessageNBT packet = new MessageNBT(message);
            final EntityPlayer player = BinnieCore.proxy.getPlayer();
            GeneTracker tracker = null;
            tracker = GeneTracker.getTracker(BinnieCore.proxy.getWorld(), player.getGameProfile());
            if (tracker != null) {
                tracker.readFromNBT(packet.getTagCompound());
            }
        }
    }
}
