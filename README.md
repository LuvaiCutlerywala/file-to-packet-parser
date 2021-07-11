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

## Future Plans ##
This is, at its core, a project to demonstrate creation of packets and transmission of packets across a network. This
project, as mentioned above only has the file parser and reconstructor, and lacks the network client for the
transmission.

- [ ] __Adding a recontructor__ : As of now, the project only has the packet parser, which can create the network
                                  packets, yet is only limited to that. The next step is to add the file reconstructor
                                  to recreate the original file from the packets.
- [ ] __Creating a network client__ : Considering this is a more basic format of packet, and also custom, it will need
                                      a custom network client to transmit the data across a network. That will probably
                                      be a separate project.

### Reading topics ###
Network packets: [Named Link]https://en.wikipedia.org/wiki/Network_packet
