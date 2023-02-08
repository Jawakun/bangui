
package javakun;

import net.minecraft.util.*;
import net.minecraft.client.gui.*;
import org.lwjgl.*;
import java.util.*;

public class BanGuiScreen extends GuiScreen
{
    public final String reason;
    public final String forumLink;
    public final String unbanLink;
    public final IChatComponent message;
    public List multilineMessage;
    public int textHeight;
    
    public BanGuiScreen(final String reason, final IChatComponent message, final String forumLink, final String unbanLink) {
        this.forumLink = forumLink;
        this.unbanLink = unbanLink;
        this.message = message;
        this.reason = reason;
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
    }
    
    public void initGui() {
        this.buttonList.clear();
        this.multilineMessage = this.fontRendererObj.listFormattedStringToWidth(this.message.getFormattedText(), this.width - 50);
        this.textHeight = this.multilineMessage.size() * this.fontRendererObj.FONT_HEIGHT;
        final int firstRowY = Math.min(this.height / 2 + this.textHeight / 2 + this.fontRendererObj.FONT_HEIGHT, this.height - 30);
        this.buttonList.add(new GuiButton(1, this.width / 2 - 102, firstRowY, 100, 20, "\u041e\u0431\u0436\u0430\u043b\u043e\u0432\u0430\u0442\u044c \u0431\u0430\u043d"));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 2, firstRowY, 100, 20, "\u041a\u0443\u043f\u0438\u0442\u044c \u0440\u0430\u0437\u0431\u0430\u043d"));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 102, firstRowY + 24, 204, 20, "\u0413\u043b\u0430\u0432\u043d\u043e\u0435 \u043c\u0435\u043d\u044e"));
    }
    
    public void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 0: {
                this.mc.displayGuiScreen((GuiScreen)new GuiMainMenu());
                break;
            }
            case 1: {
                Sys.openURL(this.forumLink);
                break;
            }
            case 2: {
                Sys.openURL(this.unbanLink);
                break;
            }
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.reason, this.width / 2, this.height / 2 - this.textHeight / 2 - this.fontRendererObj.FONT_HEIGHT * 2, 11184810);
        int i = this.height / 2 - this.textHeight / 2;
        if (this.multilineMessage != null) {
            for (final Object string : this.multilineMessage) {
                this.drawCenteredString(this.fontRendererObj, (String)string, this.width / 2, i, 16777215);
                i += this.fontRendererObj.FONT_HEIGHT;
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
