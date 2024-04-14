package uts.isd.model;

public class Customer extends User{
    
    public Customer(String _email, String _name, String _password, String _phone, String _city, String _country, String _role)
    {
        super(_email, _name, _password, _phone, _city, _country, _role);
    }
}
