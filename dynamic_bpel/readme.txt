README

This folder contains a very simple orchestration.
We will explore dynamic partner link endpoint change on the BPEL orchestration.

The orchestration is the GREETING orchestration.
He receives a name as input, and greets the name.
Exemple, input: "Antonia"; output: "Ciao Antonia"
The output string is actually defined by a service behind the orchestration (the "orchestred" service).

Our goal is to make the orchestred service dynamic.
So the orchestration receives the name as input and will call the addresser service to define which language to use (we will have one service for each language). So, the addresser will return the end point of a service, and the addresser can change the service (the language) anytime it wants.

Actually, we will make the addresser with an external operation "set language", so we can change the language. For now, we will abstract the registry (relation language x service) inside the addresser.
