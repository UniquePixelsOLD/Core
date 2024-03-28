package net.uniquepixels.core.paper;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public class TextStyle {
    public static Component PREFIX = Component.text("UniquePixels").color(TextStyle.PREFIX_COLOR).append(Component.text(" » ").color(NamedTextColor.GRAY));
    public static TextColor PREFIX_COLOR = TextColor.fromHexString("#8C0EE6");
    public static TextColor HIGHLIGHT_COLOR = TextColor.fromHexString("#CCE60E");
    public static TextColor PRIMARY_COLOR = TextColor.fromHexString("#A0A0A0");
    public static TextColor WHITE_COLOR = TextColor.fromHexString("#dcdcdc");

}
