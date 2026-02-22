public class Status {
    private String status;

    public Status(int indicador){
        switch(indicador){
            case 1:
                this.status = "TODO";
                break;
            case 2:
                this.status = "Doing";
                break;
            case 3:
                this.status = "Done";
                break;
            default:
                throw new IllegalArgumentException("Indicador inválido!");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status{" +
                "status='" + status + '\'' +
                '}';
    }
}
