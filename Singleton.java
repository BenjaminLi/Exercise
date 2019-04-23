
public class Singleton
{
    private static volatile Singleton _instance;
    private int _sharedResource;
    private static final int SERVICE_ROUNDS = 100;

    public static Singleton getInstance()
    {
        if (_instance == null)
        {
            synchronized (Singleton.class)
            {
                if (_instance == null)
                {
                    _instance = new Singleton();
                }
            }
        }
        return _instance;
    }

    private Singleton()
    {
        _sharedResource = 0;
    }

    public void service()
    {

        // fake business logic
        int businessMeaning = 0;
        for (int i = 0; i < SERVICE_ROUNDS; ++i)
        {
            businessMeaning += i * i;

            increaseSharedResource(businessMeaning);
        }
    }

    // TODO: to define more business service methods and data members

    public int getSharedResource()
    {
        synchronized (this)
        {
            return _sharedResource;
        }
    }

    public void setSharedResource(int newValue)
    {
        synchronized (this)
        {
            _sharedResource = newValue;
        }
    }

    public int increaseSharedResource(int delta)
    {
        synchronized (this)
        {
            _sharedResource += delta;

            return _sharedResource;
        }
    }
}
