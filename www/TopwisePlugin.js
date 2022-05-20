var exec = require('cordova/exec');

exports.posFunction = function(action, success, error, args){

    exec(success, error, 'TopwisePlugin', action, [args]);
}

exports.performPrint = function(success, error,args) {
    exec(success,error,'TopwisePlugin', "printAction",[args]);
}

exports.performPayment = function(success, error,args) {
    exec(success,error,'TopwisePlugin', "payAction",[args]);
}



