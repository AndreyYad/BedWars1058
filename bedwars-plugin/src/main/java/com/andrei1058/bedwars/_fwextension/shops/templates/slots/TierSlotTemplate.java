package com.andrei1058.bedwars._fwextension.shops.templates.slots;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

import static com.andrei1058.bedwars._fwextension.utils.Utils.checkNull;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unused")
public abstract class TierSlotTemplate<Price> extends SlotTemplate<Price> {

    ///! перепроверить, что в темплейтах нет особых методов на получение значений кроме обычных гетеров
    protected List<SlotTemplate<Price>> tieredSlotTemplates;

    ///! мб изменить эту логику перенятия данных с первого в тире
    public void postInit() {
        checkNull("tieredSlotTemplates", tieredSlotTemplates);
        if (tieredSlotTemplates.size() < 2) {
            throw new RuntimeException("Количество слотов в тире не может быть меньше двух.");
        }
        SlotTemplate<Price> firstSlot = tieredSlotTemplates.get(0);
        type = firstSlot.getType();
        amount = firstSlot.getAmount();
        isEnchanted = firstSlot.isEnchanted();
        name = firstSlot.getName();
        lore = firstSlot.getLore();
        itemFlags = firstSlot.getItemFlags();
        clickHandler = firstSlot.getClickHandler();
        price = firstSlot.getPrice();
        super.postInit();
    }
}
