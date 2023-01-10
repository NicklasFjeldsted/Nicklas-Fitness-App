namespace FitnessWebApi._Repositories
{

	public interface IUserRepository
	{
		public Task<List<User>> GetAll();
		public Task<User> GetById(int id);
		public Task<User> Create(User request);
		public Task<User> Update(int id, User request);
	}
	public class UserRepository : IUserRepository
	{
		private readonly DatabaseContext _context;

		public UserRepository(DatabaseContext context)
		{
			_context = context;
		}

		public async Task<List<User>> GetAll()
		{
			return await _context.User
				.Include(x => x.Gender)
				.ToListAsync();
		}

		public async Task<User> GetById(int id)
		{
			return await _context.User
				.Include(x => x.Gender)
				.Where(x => x.UserID == id)
				.FirstOrDefaultAsync();
		}

		public async Task<User> Create(User request)
		{
			request.Password = BC.HashPassword(request.Password);
			_context.Add(request);
			await _context.SaveChangesAsync();
			return await GetById(request.UserID);
		}

		public async Task<User> Update(int id, User request)
		{
			User user = await GetById(id);
			if(user != null)
			{
				user.FirstName = request.FirstName;
				user.LastName = request.LastName;
				user.BirthdayDate = request.BirthdayDate;
				user.Height = request.Height;
				user.Modified_At = DateTime.UtcNow;

				await _context.SaveChangesAsync();
			}

			return user;
		}
	}
}
