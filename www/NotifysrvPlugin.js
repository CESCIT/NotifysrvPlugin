var argscheck = require('cordova/argscheck');
var exec = require('cordova/exec');

var Notify = function() {};

Notify.prototype.send = function(message, success, error) {
    //argscheck.checkArgs('AFF', 'notify.send', arguments); 
    console.log("send notification["+message[1]+"]");
    if(!message)
        error && error("please input message");
    else
        exec(success, error, 'NotifysrvPlugin', 'send', message);
};

var notify = new Notify();
module.exports = notify;
