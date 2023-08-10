package com.orange.portail.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Result{
    public String connection_id;
    public String accept;
    public String rfc23_state;
    public Date updated_at;
    public String invitation_key;
    public Date created_at;
    public String their_role;
    public String connection_protocol;
    public String invitation_mode;
    public String routing_state;
    public String invitation_msg_id;
    public String state;
}
