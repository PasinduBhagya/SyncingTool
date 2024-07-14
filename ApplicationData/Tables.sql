CREATE TABLE Servers (
    serversID INT PRIMARY KEY AUTO_INCREMENT,
    IP_Address_1 VARCHAR(45),
    IP_Address_2 VARCHAR(45),
    username1 VARCHAR(50),
    username2 VARCHAR(50),
    password_1 VARCHAR(50),
    password_2 VARCHAR(50)
);

(ServersID, srcFolderPath, dstFolderPath, extNames, enableMonitoring, schTime, ruleName)

CREATE TABLE SyncingRules (
    ruleID INT PRIMARY KEY AUTO_INCREMENT,
    ServersID VARCHAR(45),
    srcFolderPath VARCHAR(100),
    dstFolderPath VARCHAR(100),
    extNames VARCHAR(100),
    enableMonitoring VARCHAR(10),
    schTime VARCHAR(10),
    ruleName VARCHAR(25)
);