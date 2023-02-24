/**

Transitions in various ways from one Ndef to another

*/

NdefTransit {

	var <>nodea, <>nodeb;

	*new { arg nodea, nodeb;
		^super.newCopyArgs(nodea, nodeb);
	}

	/**

	lin: performs a linear interpolation between all the
	parameters of the two nodes.

	*/

	lin {|duration=2, timestep=0.01|

		nodea.controlKeys.do {|key|

			var pa = nodea.get(key);
			var pb = nodeb.get(key);
			var steps = duration / timestep;
			var amt = (pb-pa) / steps;

			{
				steps.do {|ms|
					var val = (pa + (amt * (ms+1)));
					nodea.set(key, val.postln);
					timestep.wait;
				};

			}.fork
		}
	}

	/**

	curved: performs a curved interpolation between all the
	parameters of the two nodes.

	*/

	curved {|duration=2, timestep=0.01, curve|

		if(curve.isNil, { curve = Env([0.0,1.0],[1.0]) });

		nodea.controlKeys.do {|key|

			var pa = nodea.get(key);
			var pb = nodeb.get(key);
			var steps = duration / timestep;

			{
				(steps+1).do {|ms|
					var val = curve[(ms)/steps].linlin(0,1,pa,pb);
					nodea.set(key, val);
					timestep.wait;
				};

			}.fork
		}
	}

}
