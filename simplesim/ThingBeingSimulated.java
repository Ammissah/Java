package simplesim;

/** Interface which should be implemented by any class
 * which forms a Thing Being Simulated. 
 * It combines:
 *   TBSModel - the internal structure of a TBS
 *   TBSView - the way the TBS is displayed
 * It also extends Serializable, so any TBS can be loaded and
 * saved by serialization. However, for this to work
 * properly, any subcomponents of the TBS must also be
 * declared Serializable.
 */

public interface ThingBeingSimulated 
       extends TBSModel, TBSView, java.io.Serializable {

} // ThingBeingSimulated
