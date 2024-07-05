<?php

//A*3+B*7+C*3+D*3+E*7+F*3+G*3+H*7+I*3+J*3+K*7+L*3+M*3+N*7+O*3
$seed = [3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3]; 

$banks = [ 
    [ "name" => "ACCESS BANK", "code" => "044", "type" => "DMB" ],
    [ "name" => "CITIBANK", "code" => "023", "type" => "DMB" ],
    [ "name" => "DIAMOND BANK", "code" => "063", "type" => "DMB" ],
    [ "name" => "ECOBANK NIGERIA", "code" => "050", "type" => "DMB" ],
    [ "name" => "FIDELITY BANK", "code" => "070", "type" => "DMB" ],
    [ "name" => "FIRST BANK OF NIGERIA", "code" => "011", "type" => "DMB" ],
    [ "name" => "FIRST CITY MONUMENT BANK", "code" => "214", "type" => "DMB" ],
    [ "name" => "GUARANTY TRUST BANK", "code" => "058", "type" => "DMB" ],
    [ "name" => "HERITAGE BANK", "code" => "030", "type" => "DMB" ],
    [ "name" => "JAIZ BANK", "code" => "301", "type" => "DMB" ],
    [ "name" => "KEYSTONE BANK", "code" => "082", "type" => "DMB" ],
    [ "name" => "PROVIDUS BANK", "code" => "101", "type" => "DMB" ],
    [ "name" => "SKYE BANK", "code" => "076", "type" => "DMB" ],
    [ "name" => "STANBIC IBTC BANK", "code" => "221", "type" => "DMB" ],
    [ "name" => "STANDARD CHARTERED BANK", "code" => "068", "type" => "DMB" ],
    [ "name" => "STERLING BANK", "code" => "232", "type" => "DMB" ],
    [ "name" => "SUNTRUST", "code" => "100", "type" => "DMB" ],
    [ "name" => "UNION BANK OF NIGERIA", "code" => "032", "type" => "DMB" ],
    [ "name" => "UNITED BANK FOR AFRICA", "code" => "033", "type" => "DMB" ],
    [ "name" => "UNITY BANK", "code" => "215", "type" => "DMB" ],
    [ "name" => "WEMA BANK", "code" => "035", "type" => "DMB" ],
    [ "name" => "ZENITH BANK", "code" => "057", "type" => "DMB" ],
    [ "name" => "OFI BANK TEST", "code" => "50547", "type" => "OFI" ],
];

$accountNumber = $_GET['accountNumber'];

if(strlen($accountNumber) != 10 || !is_numeric($accountNumber)) {
    echo "Invalid Account Number";
    exit;
}

//Get the first 9 digits of the account number
$serialNumber = substr($accountNumber, 0, 9);

$foundBanks = [];

foreach ($banks as $key => $bank) {
    if (isAccountValid($accountNumber, $bank)) {
        $foundBanks[] = $bank;
    }
}

echo "<p></p>";
echo "<b>Suggested Banks: <br>";
echo json_encode($foundBanks);
exit;

function isAccountValid($accountNumber, $bank) {
    $serialNumber = substr($accountNumber, 0, 9);

    if($bank['type'] == "DMB") {
        //prefix the bank code with 0 to 6 digits
        $code = sprintf("%06d", $bank['code']);
    } else {
        //prefix the bank code with 9 to 6 digits
        $code = str_pad($bank['code'], 6, '9', STR_PAD_LEFT);
    }

    echo "Bank code: " . $code . " -- ({$bank['name']})\n";

    $checkDigit = generateCheckDigit($serialNumber, $code);

    return $checkDigit == $accountNumber[9];
}

function generateCheckDigit($serialNumber, $code)
{
    global $seed;

    //ensure that serial number is 9 digits
    $serialNumber = sprintf("%09d", $serialNumber);
    echo "serialNumber: " . $serialNumber . "\n";
    $cipher = $code . $serialNumber;
    echo "cipher: $cipher\n";
    $sum = 0;

    for ($i = 0; $i < strlen($cipher); $i++) {
        $sum += $cipher[$i] * $seed[$i];
    }

    //get remainder of sum divided by 10
    $sum %= 10;
    echo "Modulus: $sum\n";

    $checkDigit = 10 - $sum;

    $checkDigit = $checkDigit == 10 ? 0 : $checkDigit;

    echo "Returned Check Digit: " . $checkDigit . "\n\n";

    return $checkDigit;
}