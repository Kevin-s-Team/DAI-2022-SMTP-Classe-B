Email Pranks
============

Célestin Piccin & Kévin Jorand

------------

# File formats

## `config.txt`
The file should follow the syntax below. Its encoding must be UTF8, but line endings are unspecified.
```
localhost
25
7
```
Where `localhost` is the host to connect to, `25` the port to use and `7` the number of groupes to use.

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