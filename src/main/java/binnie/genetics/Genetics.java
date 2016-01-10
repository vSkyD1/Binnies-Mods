package binnie.genetics;

import binnie.core.AbstractMod;
import binnie.core.BinnieCore;
import binnie.core.gui.IBinnieGUID;
import binnie.core.machines.MachineGroup;
import binnie.core.network.BinniePacketHandler;
import binnie.core.network.IPacketID;
import binnie.core.proxy.IProxyCore;
import binnie.genetics.core.GeneticsGUI;
import binnie.genetics.core.GeneticsPacket;
import binnie.genetics.item.ItemAnalyst;
import binnie.genetics.item.ItemDatabase;
import binnie.genetics.item.ItemSerumArray;
import binnie.genetics.item.ModuleItem;
import binnie.genetics.machine.ModuleMachine;
import binnie.genetics.proxy.Proxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.Item;

@Mod(
   modid = "Genetics",
   name = "Genetics",
   useMetadata = true,
   dependencies = "after:BinnieCore"
)
public class Genetics extends AbstractMod {
   public static ItemSerumArray itemSerumArray = null;
   @Instance("Genetics")
   public static Genetics instance;
   @SidedProxy(
      clientSide = "binnie.genetics.proxy.ProxyClient",
      serverSide = "binnie.genetics.proxy.ProxyServer"
   )
   public static Proxy proxy;
   public static String channel = "GEN";
   public static Item itemGenetics;
   public static Item itemSerum;
   public static Item itemSequencer;
   public static MachineGroup packageGenetic;
   public static MachineGroup packageAdvGenetic;
   public static MachineGroup packageLabMachine;
   public static ItemDatabase database;
   public static ItemAnalyst analyst;
   public static Item registry;
   public static Item masterRegistry;

   @EventHandler
   public void preInit(FMLPreInitializationEvent evt) {
      this.addModule(new ModuleItem());
      this.addModule(new ModuleMachine());
      this.preInit();
   }

   @EventHandler
   public void init(FMLInitializationEvent evt) {
      this.init();
   }

   @EventHandler
   public void postInit(FMLPostInitializationEvent evt) {
      this.postInit();
   }

   public Genetics() {
      super();
      instance = this;
   }

   public IBinnieGUID[] getGUIDs() {
      return GeneticsGUI.values();
   }

   public IPacketID[] getPacketIDs() {
      return GeneticsPacket.values();
   }

   public String getChannel() {
      return "GEN";
   }

   public IProxyCore getProxy() {
      return proxy;
   }

   public String getModID() {
      return "genetics";
   }

   protected Class getPacketHandler() {
      return Genetics.PacketHandler.class;
   }

   public boolean isActive() {
      return BinnieCore.isGeneticsActive();
   }

   public static class PacketHandler extends BinniePacketHandler {
      public PacketHandler() {
         super(Genetics.instance);
      }
   }
}
