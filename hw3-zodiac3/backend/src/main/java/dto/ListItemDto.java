package dto;

import java.util.ArrayList;
import java.util.List;

public class ListItemDto {
    public List<ItemDto> itemDtoList;

    public ListItemDto(){
        this.itemDtoList = new ArrayList<ItemDto>();
    }

    public ListItemDto(List<ItemDto> itemDtoList) {
        this.itemDtoList =  new ArrayList<ItemDto>(itemDtoList);
    }

    public void AddItemDto(ItemDto itemDto){
        try {
            this.itemDtoList.add(itemDto);
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
        }
    }

    public void PrintAllItems() {
        for (int i=0; i<itemDtoList.size(); i++) {
            ItemDto item_dto = (ItemDto) this.itemDtoList.get(i);
            System.out.println(item_dto.name + item_dto.price);
        }

    }
    @Override
    public String toString() {
        return "ListItemDto{" +
                "itemDtoList=" + itemDtoList +
                '}';
    }
}