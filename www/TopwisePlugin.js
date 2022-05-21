var exec = require('cordova/exec');

exports.posFunction = function(args,action, success, error){

    exec(success, error, 'TopwisePlugin', action, [args]);
}

exports.performPrint = function(args, success, error) {
    exec(success,error,'TopwisePlugin', "printAction",[args]);
}

exports.performPayment = function(args,success, error) {
    exec(success,error,'TopwisePlugin', "payAction",[args]);
}



