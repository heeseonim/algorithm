var HashMap = function() {
    this.map = new Array();
};

HashMap.prototype = {
    put: function(key, value) {
        return this.map[key] = value;
    },
    get: function(key) {
        return this.map[key];
    },
    getOrDefault: function(key, defaultValue) {
        if (this.map[key]) {
            return this.map[key]
        } else {
            return defaultValue
        }
    }
};

function solution(participant, completion) {
    var map = new HashMap();

    for(var i in participant) {
        map.put(participant[i], map.getOrDefault(participant[i], 0) + 1);
    }
    for(var i in completion) {
        map.put(completion[i], map.get(completion[i]) - 1);
    }

    var answer = '';

    for(i in participant) {
        if (map.get(participant[i]) > 0) {
            answer = participant[i];
        }
    }

    return answer;
}