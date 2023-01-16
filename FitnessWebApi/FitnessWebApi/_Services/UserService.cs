namespace FitnessWebApi._Services
{
	public interface IUserService
	{
		public Task<List<StaticUserResponse>> GetAll();
		public Task<DirectUserResponse> GetById(int id);
		public Task<DirectUserResponse> Create(UserRequest request);
		public Task<DirectUserResponse> Update(int id, UserRequest request);
	}

	public class UserService : IUserService
	{
		private readonly IUserRepository _repository;
		private readonly IMapper m_mapper;

		public UserService(IUserRepository repository, IMapper mapper)
		{
			_repository = repository;
			m_mapper = mapper;
		}

		public async Task<List<StaticUserResponse>> GetAll()
		{
			List<User> users = await _repository.GetAll();
			if(users != null)
			{
				return users.Select(user => m_mapper.Map<User, StaticUserResponse>(user)).ToList();
			}

			return null;
		}

		public async Task<DirectUserResponse> GetById(int id)
		{
			User user = await _repository.GetById(id);
			if (user != null)
			{
				return m_mapper.Map<DirectUserResponse>(user);
			}

			return null;
		}

		public async Task<DirectUserResponse> Create(UserRequest request)
		{
			User user = await _repository.Create(m_mapper.Map<User>(request));
			if (user != null)
			{
				return m_mapper.Map<DirectUserResponse>(user);
			}

			return null;
		}

		public async Task<DirectUserResponse> Update(int id, UserRequest request)
		{
			User user = await _repository.Update(id, m_mapper.Map<User>(request));
			if (user != null)
			{
				return m_mapper.Map<DirectUserResponse>(user);
			}

			return null;
		}
	}
}
