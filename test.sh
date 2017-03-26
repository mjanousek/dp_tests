#!/bin/bash

function print_result {
	echo -n $1
	if [[ $2 == 0 ]]; then
		echo -e " \e[32mPASS\e[39m"
	else
		echo -e " \e[31mFAILED\e[39m"
	fi
}


javac src/cz/janousek/*.java

cd "../roadrunner"

# Simple contract validation
out=$((rrrun "-classpath=../test/src -tool=CT -contractFile=contract_simple_violation cz.janousek.NoViolation") 2>&1)
count=$(grep -oPm1 "(?<=<errorType> <name> ContractTool </name> <count>)[^<]+" <<< "$out")
cnd=0; [ $count==0 ] && res=1
print_result "[TEST] No contract violation" $cnd

# Simple contract validation
out=$((rrrun "-classpath=../test/src -tool=CT -contractFile=contract_simple_violation cz.janousek.SimpleVectorClockViolation") 2>&1)
count=$(grep -oPm1 "(?<=<errorType> <name> ContractTool </name> <count>)[^<]+" <<< "$out")
cnd=0; [ $count>0 ] && res=1
print_result "[TEST] Test simple program" $cnd

