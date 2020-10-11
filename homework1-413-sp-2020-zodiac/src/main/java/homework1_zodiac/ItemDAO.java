package homework1_zodiac;

import homework1_zodiac.dtos.DTO;
import homework1_zodiac.dtos.ItemDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class ItemDAO {
    List<DTO> itemList = new ArrayList<>();
    Set<String> idSet = new HashSet<>();

    //Singleton w/ Lazy Loading
    private static ItemDAO instance;

    public static ItemDAO getInstance() {
        if (instance == null) {
            instance = new ItemDAO();
        }

        return instance;
    }

    //private constructor ensures people use getInstance()
    private ItemDAO() {
    }

    public void addItem(ItemDTO newItem) {
        itemList.add(newItem);
        idSet.add(newItem.getMachineCode());
    }

    public Set<String> getAllMachineCodes() {
        return idSet;
    }

    public List<DTO> listItems() {
        return itemList;
    }

}