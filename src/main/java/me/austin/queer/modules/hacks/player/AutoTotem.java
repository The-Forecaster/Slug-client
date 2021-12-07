package me.austin.queer.modules.hacks.player;

import me.austin.queer.modules.hacks.Category;
import me.austin.queer.modules.hacks.Hack;
import me.austin.queer.modules.setting.settings.KeyBindSetting;
import me.austin.queer.modules.setting.settings.ModeSetting;
import me.austin.queer.util.entity.player.PlayerUtil;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

@Hack.Register(name = "Auto Totem", description = "Places a totem in your offhand", bind = KeyBindSetting.NONE, category = Category.PLAYER)
public class AutoTotem extends Hack {
    public final ModeSetting item = this.register(new ModeSetting("Item", "Which item should it put in your offhand", TargetItem.TOTEM, TargetItem.values(), this));
    
    Item targetitem;

    public AutoTotem() {
        super(AutoTotem.class.getAnnotation(Hack.Register.class));
    }

    @Override
    public void onUpdate() {
        if (mc.player.getOffHandStack().getItem() != Items.TOTEM_OF_UNDYING && PlayerUtil.find(Items.TOTEM_OF_UNDYING) > -1) {
            PlayerUtil.setOffhand(PlayerUtil.find(Items.TOTEM_OF_UNDYING));
        }
    }

    protected static enum TargetItem {
        TOTEM(Items.TOTEM_OF_UNDYING),
        GAPPLE(Items.ENCHANTED_GOLDEN_APPLE),
        CRYSTAL(Items.END_CRYSTAL);

        private final Item item;
        
        TargetItem(Item item) {
            this.item = item;
        }

        public Item getItem() {
            return this.item;
        }
    }
}
