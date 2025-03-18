public class AccessorFactory
{
    public Accessor createAccessor(AccessorType accessorType)
    {
        switch (accessorType) {
            case XML -> {
                return new XMLAccessor();
            }
            case DEMO -> {
                return new DemoPresentation();
            }
            default -> {
                return null;
            }
        }
    }
}
