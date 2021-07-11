# Packet File Format (.packet) #
This is a file format created specifically for the purpose of storing the packet data. This is a simple packet creator,
with very limited functionality. It has a checksum, a UNIX timestamp, a sequence number and the byte payload. The
format is create simply to hold the data in a way that will allow for a custom packet re-stitcher in the future to be
able to stitch the packets back together into their complete file.

## The syntax ##
The syntax, as described above has a very simple flow to it, with the sequence number coming first, then the timestamp,
then the checksum, then the data. Each piece of validation data is added with a semicolon in front of it and behind it,
sort of like assembly comments. To note, the payload is not delimited by the semicolons.

### Validation data ###
To reiterate, each piece of validation data is enclosed by semicolons, in the form ;<data>;
- __Sequence Number__ : As the sequence number is vital to recreating the file, it is the first item.
- __Timestamp__ : The timestamp is a UNIX timestamp, which is the number of seconds elapsed from 1 January 1970,
                  midnight.
- __CheckSum__ : The checkSum is generated through a SHA-256 algorithm, and is based on the unix timestamp of the
                 packet.

### Payload ###
The data is simply a set number of bytes that is written to each packet from the file. It should stitch back together
with all the original formatting/the original structure, but I don't know for sure. The raw bytes are directly read
from the origin file and directly written to the target files. There is no processing of the data.

## Future plans ##
- [ ] To include some way of checking if the data is corrupted.
- [ ] To include charset encoding information for the validation data.
