# Run The Demo! <!-- omit in toc -->

In this demo, we'll  use our familiar CenterInv ACA-Py agent to issue authentication credentials to patients, granting them access to the portal. This time the patient will use the [application](https://gitlab.tech.orange/salma.bradai/pfe-gestion-de-consentement-patient/-/blob/main/application/README.md) (mobile wallet) to accept the cresential and to provide their consent in medical researchs and then the Medical Center can check the consent with the Investigator Center to provide data access. To do this we need to run the centerInv agent on a publicly accessible port, and Alice will need a compatible mobile wallet.

# Contents <!-- omit in toc -->

- [Getting Started](#getting-started)
  - [Get a mobile agent](#get-a-mobile-agent)
  - [Running The Agents](#running-the-agents)
    - [Run `patient`](#run-patient)
    - [Run `centerMed`](#run-centerMed)
  - [Run `centerInv`](#run-centerInv)
  - [Run an instance of indy-tails-server](#run-an-instance-of-indy-tails-server)
  - [Run `centerInv` With Extra Parameters](#run-centerInv-with-extra-parameters)
- [Accept the Invitation](#accept-the-invitation)
- [Issue a Credential](#issue-a-credential)
  - [Accept the Credential](#accept-the-credential)
- [Issue a Presentation Request](#issue-a-presentation-request)
- [Present the Proof](#present-the-proof)
- [Review the Proof](#review-the-proof)
- [Conclusion](#conclusion)

# Getting Started

This demo can be run on your local machine, and will demonstrate credential exchange and proof exchange as well as revocation with a mobile agent. 

If you are not familiar with how revocation is currently implemented in Hyperledger Indy, [this article](https://github.com/hyperledger/indy-hipe/tree/master/text/0011-cred-revocation) provides a good background on the technique. A challenge with revocation as it is currently implemented in Hyperledger Indy is the need for the prover (the agent creating the proof) to download tails files associated with the credentials it holds.

## Get a mobile agent

Of course for this, you need to have a mobile agent. To find, install and setup a compatible mobile agent, follow the instructions [here](https://github.com/bcgov/identity-kit-poc/blob/master/docs/GettingApp.md).

## Running The Agents

Open a new bash shell and in a project directory run the following:

```bash
git clone https://gitlab.tech.orange/salma.bradai/pfe-gestion-de-consentement-patient.git Agents 
```

### Run `patient` 

To run the Investigator Center agent, execute the following command:

```bash
cd Agents/demo
LEDGER_URL=http://test.bcovrin.vonx.io ./run_demo patient
```

### Run `centerMed` 

To run the Investigator Center agent, execute the following command:

```bash
cd Agents/demo
LEDGER_URL=http://test.bcovrin.vonx.io ./run_demo centerMed
```

### Run `centerInv` 

#### Install ngrok and jq

[ngrok](https://ngrok.com/) is used to expose public endpoints for services running locally on your computer.

[jq](https://github.com/stedolan/jq) is a json parser that is used to automatically detect the endpoints exposed by ngrok.

You can install ngrok from [here](https://ngrok.com/)

You can download jq releases [here](https://github.com/stedolan/jq/releases)

#### Expose services publicly using ngrok

Since the mobile agent will need some way to communicate with the agent running on your local machine in docker, we will need to create a publicly accesible url for some services on your machine. The easiest way to do this is with [ngrok](https://ngrok.com/). Once ngrok is installed, create a tunnel to your local machine:

```bash
ngrok http 8020
```

This service is used for your local aca-py agent - it is the endpoint that is advertised for other Aries agents to connect to.

You will see something like this:

```
Forwarding                    http://abc123.ngrok.io -> http://localhost:8020
Forwarding                    https://abc123.ngrok.io -> http://localhost:8020
```

This creates a public url for ports 8020 on your local machine.

Note that an ngrok process is created automatically for your tails server.

Keep this process running as we'll come back to it in a moment.

### Run an instance of indy-tails-server

For revocation to function, we need another component running that is used to store what are called tails files.

If you are not running with revocation enabled you can skip this step.

Open a new bash shell, and in a project directory, run:

```bash
git clone https://github.com/bcgov/indy-tails-server.git
cd indy-tails-server/docker
./manage build
./manage start
```

This will run the required components for the tails server to function and make a tails server available on port 6543.

This will also automatically start an ngrok server that will expose a public url for your tails server - this is required to support mobile agents. The docker output will look something like this:

```bash
ngrok-tails-server_1  | t=2020-05-13T22:51:14+0000 lvl=info msg="started tunnel" obj=tunnels name="command_line (http)" addr=http://tails-server:6543 url=http://c5789aa0.ngrok.io
ngrok-tails-server_1  | t=2020-05-13T22:51:14+0000 lvl=info msg="started tunnel" obj=tunnels name=command_line addr=http://tails-server:6543 url=https://c5789aa0.ngrok.io
```

Note the server name in the `url=https://c5789aa0.ngrok.io` parameter (`https://c5789aa0.ngrok.io`) - this is the external url for your tails server. Make sure you use the `https` url!


### Run `centerInv` With Extra Parameters

To run the Investigator Center agent, execute the following command:

```bash
cd Agents/CenterInv/demo
TAILS_NETWORK=docker_tails-server LEDGER_URL=http://test.bcovrin.vonx.io ./run_demo centerInv --aip 10 --events
```

(Note that we have to start centerInv with `--aip 10` for compatibility with mobile clients.)

The `TAILS_NETWORK` parameter lets the demo script know how to connect to the tails server (which should be running in a separate shell on the same machine).

## Accept the Invitation

When the Investigator Center agent starts up it automatically creates an invitation and generates a QR code on the screen. On your mobile app, select "SCAN CODE" (or equivalent) and point your camera at the generated QR code. The mobile agent should automatically capture the code and confirm the connection. 

## Issue a Credential

We will use the Investigator Center console to issue a credential. This could be done using the Swagger API as we have done in this [link](https://github.com/0farah/GDCP.git/-/blob/main/API.md).

In the Investigator Center console, select option `1` to send a credential to the mobile agent.

The centerInv agent outputs details to the console; e.g.,

```text
CenterInv      | Credential: state = credential-issued, cred_ex_id = ba3089d6-92da-4cb7-9062-7f24066b2a2a
CenterInv      | Revocation registry ID: CMqNjZ8e59jDuBYcquce4D:4:CMqNjZ8e59jDuBYcquce4D:3:CL:50:centerInv.agent.patient_schema:CL_ACCUM:4f4fb2e4-3a59-45b1-8921-578d005a7ff6
CenterInv      | Credential revocation ID: 1
CenterInv      | Credential: state = done, cred_ex_id = ba3089d6-92da-4cb7-9062-7f24066b2a2a
```

### Accept the Credential

The credential offer should automatically show up in the mobile agent. Accept the offered credential following the instructions provided by the mobile agent. 

## Issue a Presentation Request

We will use the Investigator Center console to ask mobile agent for a proof.We need to make this step to get the patient authenticate to the portal. This also could be done using the Swagger API.

In the CenterInv console, select option `2` to send a proof request to the mobile agent.

## Present the Proof

The presentation (proof) request should automatically show up in the mobile agent. Follow the instructions provided by the mobile agent to prepare and send the proof back to CenterInv. 

If the mobile agent is able to successfully prepare and send the proof, you can go back to the terminal to see the status of the proof.


## Review the Proof

In the Investigator Center console window, the proof should be received as validated.

## Conclusion

That’s the demo. Feel free to play with the Swagger API and experiment further and figure out what an instance of a controller has to do to make things work.

<!-- Docs to Markdown version 1.0β17 -->