# File To Packet Parser #
## Project Overview ##
This is a simple project that takes any file and converts it into custom packets, that then are stored on the machine.
It is meant to be a good analogue to understanding how networks parse their packets and send them. This project is only
meant to include the file parser and file reconstructor, and the goal is to also have a custom network client for the
packets in the future.

## .packet File Format ##
This file format is a very basic one, created especially for the purpose of persisting the data on the packets. It is
 meant to be a easy-to-understand file format, with simple validation checks and syntax. For exact details on what
 validation is used, please check out the file specifications in the resources folder.

## Usage ##
This project is created for demonstration purposes. The usage is just understanding how the algorithm for parsing,
transmitting, and reconstructing files from packets works across a network.
### How to use on the command line ###
Use the javac command to compile the code, and then to run use the java command. Remember that the first argument is
the target file to be parsed, the second one being the size of the payload of data in each packet. The files are then
saved in a subfolder called Packets in the documents folder on Windows.

## Future Plans ##
This is, at its core, a project to demonstrate creation of packets and transmission of packets across a network. This
project, as mentioned above only has the file parser and reconstructor, and lacks the network client for the
transmission.

- [ ] __Adding a reconstructor__ : As of now, the project only has the packet parser, which can create the network
                                  packets, yet is only limited to that. The next step is to add the file reconstructor
                                  to recreate the original file from the packets.
- [ ] __Creating a network client__ : Considering this is a more basic format of packet, and also custom, it will need
                                      a custom network client to transmit the data across a network. That will probably
                                      be a separate project.

### Reading topics ###
 - __Network packets__: https://en.wikipedia.org/wiki/Network_packet
