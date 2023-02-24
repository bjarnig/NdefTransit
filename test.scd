/**

Test NdefTransit

*/

(

Ndef.clear;

Ndef(\a, {|freq=60,lpf=100| LPF.ar(Saw.ar(freq!2,0.1),lpf) }).play;
Ndef(\aa, {|freq=2000,lpf=8000| LPF.ar(Saw.ar(freq!2,0.1),lpf) });

n = NdefTransit( Ndef(\a), Ndef(\aa));

)

n.lin(2,0.1)

n.curved(2,0.1,Env([1.0,0.15,1.0,0.2,0.0],0.25!4))
