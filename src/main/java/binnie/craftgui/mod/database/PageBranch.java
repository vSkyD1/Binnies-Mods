package binnie.craftgui.mod.database;

import binnie.craftgui.core.IWidget;
import binnie.craftgui.mod.database.DatabaseTab;
import binnie.craftgui.mod.database.PageAbstract;

abstract class PageBranch extends PageAbstract {
   public PageBranch(IWidget parent, DatabaseTab tab) {
      super(parent, tab);
   }
}
