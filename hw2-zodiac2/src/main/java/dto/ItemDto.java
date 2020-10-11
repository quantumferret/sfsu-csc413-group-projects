package dto;

public class ItemDto implements Dto {
    private String name;
    private String price;
    private String machineCode;
    

    public ItemDto(String name, String price, String machineCode) {
        this.name = name;
        this.price = price;
        this.machineCode = machineCode;
    }

    public ItemDto() {

    }

    public String getName() {
        return name;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}