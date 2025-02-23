package com.andrei1058.bedwars._core.items;

import com.andrei1058.bedwars._core.BwObject;
import com.andrei1058.bedwars.arena.IArena;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import static com.andrei1058.bedwars._core.utils.BwIdentifierUtils.eraseIdentifier;
import static com.andrei1058.bedwars._core.utils.BwIdentifierUtils.writeIdentifier;

@SuppressWarnings("unused")
///! подумать над разделением на два класса
public abstract class BwItem extends BwObject {

    private ItemStack stackState;
    private Item entityState;

    protected BwItem(ItemStack itemStack, String id, IArena arena) {
        super(arena, id);
        toStackState(itemStack);
    }
    protected BwItem(Item itemEntity, String id, IArena arena) {
        super(arena, id);
        toEntityState(itemEntity);
    }

    public void destruct() {
        if (isStack()) {
            eraseIdentifier(stackState);
        }
        if (isEntity()) {
            eraseIdentifier(entityState);
        }
    }

    protected void toStackState(ItemStack itemStack) {
        this.stackState = itemStack;
        writeIdentifier(stackState, this.getId());
        this.entityState = null;
    }
    protected void toEntityState(Item itemEntity) {
        this.entityState = itemEntity;
        writeIdentifier(entityState, this.getId());
        this.stackState = null;
    }

    public boolean isStack() {
        return stackState != null;
    }
    public boolean isEntity() {
        return entityState != null;
    }

    public ItemStack asStack() {
        return stackState;
    }
    public Item asEntity() {
        return entityState;
    }

}
