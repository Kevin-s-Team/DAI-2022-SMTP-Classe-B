Email Pranks
============

Célestin Piccin & Kévin Jorand

------------

TODO
A brief description of your project: if people exploring GitHub find your repo, without a prior knowledge of the API course, they should be able to understand what your repo is all about and whether they should look at it more closely.

# File formats

## `config.txt`
The file should follow the syntax below. Its encoding must be UTF8, but line endings are unspecified.
```
localhost
25
7
```
Where `localhost` is the host to connect to (IPV4 address or `localhost`), `25` the port to use and `7` the number of groups to use.

## `addresses.txt`
The file should follow the syntax below. Its encoding must be UTF8, but line endings are unspecified. Each line should contain exactly 1 e-mail address.
```
recipient1@domain1.ch
recipient2@domain2.ch
recipient3@domain3.ch

[..]

recipientN@domainN.ch
```

## `messages.txt`
The file should follow the syntax below. Its encoding must be UTF8 but line endings are unspecified. Messages are separated by a line containing only a single `.`.
```
subject1 subject1 subject1
body1 body1 body1
body1 body1
body1
.
subject2 subject2 subject2
body2 body2 body2
body2 body2
body2
.

[...]

.
subjectN subjectN subjectN
bodyN bodyN bodyN
bodyN bodyN
bodyN
.
```

TODO
What is MockMock (or any other mock SMTP server you decided to use)?

TODO
Instructions for setting up your mock SMTP server (with Docker - which you will learn all about in the next 2 weeks). The user who wants to experiment with your tool but does not really want to send pranks immediately should be able to use a mock SMTP server. For people who are not familiar with this concept, explain it to them in simple terms. Explain which mock server you have used and how you have set it up.

TODO
Clear and simple instructions for configuring your tool and running a prank campaign. If you do a good job, an external user should be able to clone your repo, edit a couple of files and send a batch of e-mails in less than 10 minutes.

TODO
A description of your implementation: document the key aspects of your code. It is a good idea to start with a class diagram. Decide which classes you want to show (focus on the important ones) and describe their responsibilities in text. It is also certainly a good idea to include examples of dialogues between your client and an SMTP server (maybe you also want to include some screenshots here).
