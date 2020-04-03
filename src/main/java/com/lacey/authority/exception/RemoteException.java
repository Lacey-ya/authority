package com.lacey.authority.exception;

public class RemoteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RemoteException(){

    }

    public RemoteException(String message){
        super(message);
    }

    public RemoteException(String message, Throwable cause){
        super(message, cause);
    }
}
