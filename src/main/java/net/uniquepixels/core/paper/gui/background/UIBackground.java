package net.uniquepixels.core.paper.gui.background;


import net.uniquepixels.core.paper.gui.item.UIItem;

import java.util.List;

public record UIBackground(BackgroundType type, List<UIItem> backgroundItems) {



  public enum BackgroundType {
    FULL,
    NONE,
    SELF,
    BOTTOM_LINE
  }

}
