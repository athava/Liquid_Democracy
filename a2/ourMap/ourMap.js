function ourMap(array) {
 // What do we have to hold/track???
 var original=array;//TO COMPLETE!
 var current=original;
 return (function mapWithCallback(fn, callback) {
 // If fn is not a function return current Array
 if( typeof(fn) !== 'function'){
   return current;
 }
 // map fn to array and save result to current array
current = current.map(fn);
// If callback is a function, execute callback
 if( typeof(callback) === 'function'){
   callback.call(current, original);
 }
 return mapWithCallback; 
 })
}