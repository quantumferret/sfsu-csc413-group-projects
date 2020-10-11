package dto;

public class PaymentDto implements Dto {
    private String name;
    private String machineCode;


    public PaymentDto(String name, String machineCode) {
        this.name = name;
        this.machineCode = machineCode;
    }

    public PaymentDto() {

    }

    public String getName() {
        return name;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

}
