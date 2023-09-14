## From the patient or the Center Inv Swagger interface:

•	Receive a new connection invitation
/connections/receive-invitation
{
"@type": "did:sov:BzCbsNYhMrjHiqZDTUASHg;spec/connections/1.0/invitation",
 "@id": "7fd7de31-e5fd-4751-a374-35068447ebdc", 
"recipientKeys": 
["7vTStUDrKPdJN5yctjJHohZDxhuuNevcSEEMr7K6NbFF"], 
"label": "faber.agent", 
"serviceEndpoint": http://host.docker.internal:8030
}
 
## From the center Inv swagger interface:

•	Sends a credential definition to the ledger
/credential-definitions
{
  "revocation_registry_size": 1000,
  "schema_id": "Fty3a7szAjZANKCZDA3ws8:2:patient schema:13.75.82",
  "support_revocation": true,
  "tag": "default"
}
 
•	Create a credential offer and send it
/issue-credential/create-offer
{
            "connection_id": patient.connection_id,
            "comment": "Offer on cred def id GtLBw5FvnrTAYwgTd7B5ag:3:CL:218699:centerInv.agent.patient_schema",
            "auto_remove": false,
            "credential_preview": {
                "@type": "https://didcomm.org/issue-credential/2.0/credential-preview",
                "attributes": [
                    {
                        "name": "ref",
                        "value": `${patient.ref}`
                    },
                    {
                        "name": "disease",
                        "value": patient.disease
                    },
                    {
                        "name": "gender",
                        "value": patient.gender
                    },
                    {
                        "name": "date",
                        "value": patient.date
                    }

                ]
            },
            "filter": {
                "indy": {
                    "cred_def_id": credential_definition_ids[0]
                }
            },
            "trace": false
        }


 
•	Send proof request
/present-proof-2.0/send-request
{
     "presentation_request":{
        "indy":{
           "name":"Proof of The patient",
           "version":"1.0",
           "requested_attributes":{
              "0_ref_uuid":{
                 "name":"ref",
                 "restrictions":[
                    {
                       "schema_name":"patient schema"
                    }
                 ]
              },
              "0_gender_uuid":{
                 "name":"gender",
                 "restrictions":[
                    {
                       "schema_name":"patient schema"
                    }
                 ]
              },
              "0_disease_uuid":{
                 "name":"disease",
                 "restrictions":[
                    {
                       "schema_name":"patient schema"
                    }
                 ]
              },
              "0_date_uuid":{
                 "name":"date",
                 "restrictions":[
                    {
                       "schema_name":"patient schema"
                    }
                 ]
              }
           },
           "requested_predicates":{
              "0_ref_GE_uuid":{
                 "name":"ref",
                 "p_type":">",
                 "p_value":1,
                 "restrictions":[
                    {
                       "schema_name":"patient schema"
                    }
                 ]
              }
           }
        }
     },
     "trace":false,
     "connection_id":`${formValue.password}`
  }


##	From the patient swagger interface:

•	Create a credential offer and send it
/issue-credential/send-offer
{
      "connection_id": `${connectionId}`,
      "comment": "Offer on cred def id W1izxRRSiEwMnm27hCRvWs:3:CL:218730:patient.agent.consent_schema",
      "auto_remove": false,
      "credential_preview": {
        "@type": "https://didcomm.org/issue-credential/2.0/credential-preview",
        "attributes": [
          {
            "name": "patient_ref",
            "value": `${userRef}`
          },
          {
            "name": "medical_ref",
            "value": "1111111"
          },
          {
            "name": "consent",
            "value": "True"
          }
        ]
      },
      "filter": {
        "indy": {
          "cred_def_id": `${credential_definition_ids[0]}`
        }
      },
      "trace": false
    }


## From the centerMed swagger interface:

•	Receive a new connection invitation from the centerInv
/connections/receive-invitation
{
"@type": "did:sov:BzCbsNYhMrjHiqZDTUASHg;spec/connections/1.0/invitation",
 "@id": "7fd7de31-e5fd-4751-a374-35068447ebdc", 
"recipientKeys": 
["7vTStUDrKPdJN5yctjJHohZDxhuuNevcSEEMr7K6NbFF"], 
"label": "faber.agent", 
"serviceEndpoint": http://host.docker.internal:8030
}


•	Send proof request of the consent 
/present-proof-2.0/send-request
{
            "presentation_request": {
                "indy": {
                    "name": "Proof of Consent",
                    "version": "1.0",
                    "requested_attributes": {
                        "0_consent_uuid": {
                            "name": "consent",
                            "restrictions": [
                                {
                                    "schema_name": "consent schema"
                                }
                            ]
                        },
                        "0_patient_ref_uuid": {
                            "name": "patient_ref",
                            "restrictions": [
                                {
                                    "schema_name": "consent schema"
                                }
                            ]
                        }
                    },
                    "requested_predicates": {
                        "0_consent_GE_uuid": {
                            "name": "medical_ref",
                            "p_type": ">=",
                            "p_value": 0,
                            "restrictions": [
                                {
                                    "schema_name": "consent schema"
                                }
                            ]
                        }
                    }
                }
            },
            "trace": false,
            "connection_id": `${connectionId}`
        }
 