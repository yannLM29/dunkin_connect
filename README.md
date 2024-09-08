# Overview
Dunkin Connect is a Minecraft plugin that allows you to upload statistical information about the players to a MySQL database.

# How to use
To use this plugin you need to configure the config file /plugins/dunkin_connect/config.yml on your server.
Your Mysql/MariaDb server needs to have a database called *dunkin_connect_db* in which you have two tables:

### Players
| id        | pseudo         | nb_of_kills     | is_connected    | pos_x           | pos_y           | pos_z           | nb_of_kills     |
| :---      |    :----:      |     :----:      |     :----:      |     :----:      |     :----:      |     :----:      |          :----: |
| int       | varchar(>16)   | int             | tinyint         | bigint          | bigint          | bigint          | int             |

### Session
| id        | pseudo         | start           | duration        |
| :---      |    :----:      |     :----:      |     :----:      |
| int       | varchar(>16)   | timestamp       | time            |