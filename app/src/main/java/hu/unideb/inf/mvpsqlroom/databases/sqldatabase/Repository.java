package hu.unideb.inf.mvpsqlroom.databases.sqldatabase;

public class Repository {

    public CommunicatorTypeEnums CommunicatorTypes;
    public Communicator Communicator;

    public Repository(CommunicatorTypeEnums enumType){
        this.CommunicatorTypes = enumType;

        if(enumType.equals(CommunicatorTypeEnums.MsSQLServer)){
            this.Communicator = new SQLDatabase();
        }
        else {
            this.Communicator = null;
        }
    }
}

