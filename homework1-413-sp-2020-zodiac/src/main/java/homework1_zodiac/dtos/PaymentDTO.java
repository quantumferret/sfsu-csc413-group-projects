package homework1_zodiac.dtos;

public class PaymentDTO implements DTO {
    private String name;
    private String machineCode;


    public PaymentDTO(String name, String machineCode) {
        this.name = name;
        this.machineCode = machineCode;
    }

    public PaymentDTO() {

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
