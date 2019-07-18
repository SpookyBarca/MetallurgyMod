package com.ssbbpeople.metallurgy.util.ModEnums;

import net.minecraft.tileentity.TileEntity;

public class ModEnums {

    public enum GUI_ENUM {
        GRINDER(TileEntityGrinder.class, 1);

        private final Class<? extends TileEntity> Tile;
        private final int ReferencedGUI;

        GUI_ENUM (Class<? extends TileEntity> TileClass, int GuiID){
            this.Tile = TileClass;
            this.ReferencedGUI = GuiID;
        }

        public Class<? extends TileEntity> getTile (){
            return this.Tile;
        }

        public int getGID () {
            return this.ReferencedGUI;
        }

        public static GUI_ENUM getByID ( int SearchID) {
            switch (SearchID){
                case 1:
                    return GRINDER;
                default:
                    return null;
            }
        }
    }
	
}
