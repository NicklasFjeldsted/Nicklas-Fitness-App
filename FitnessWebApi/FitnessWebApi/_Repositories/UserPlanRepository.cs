namespace FitnessWebApi._Repositories
{
	public interface IUserPlanRepository
	{
		public Task<List<UserPlan>> GetAll();
		public Task<UserPlan> GetById(int id);
		public Task<UserPlan> Create(UserPlan request);
		public Task<UserPlan> Update(int id, UserPlan request);
		public Task<UserPlan> Delete(UserPlan userPlan);
	}
	public class UserPlanRepository : IUserPlanRepository
	{
		private readonly DatabaseContext _context;

		public UserPlanRepository(DatabaseContext context)
		{
			_context = context;
		}

		public async Task<List<UserPlan>> GetAll()
		{
			return await _context.UserPlan
				.Include(x => x.User)
				.Include(x => x.ActivityLevel)
				.Include(x => x.PlanProgress)
				.ToListAsync();
		}

		public async Task<UserPlan> GetById(int id)
		{
			return await _context.UserPlan
				.Include(x => x.User)
				.Include(x => x.ActivityLevel)
				.Include(x => x.PlanProgress)
				.Where(x => x.UserPlanID == id)
				.FirstOrDefaultAsync();
		}

		public async Task<UserPlan> Create(UserPlan request)
		{
			_context.UserPlan.Add(request);
			await _context.SaveChangesAsync();
			return await GetById(request.UserPlanID);
		}

		public async Task<UserPlan> Update(int id, UserPlan request)
		{
			UserPlan userPlan = await GetById(id);
			if(userPlan != null)
			{
				userPlan.StartWeight = request.StartWeight;
				userPlan.StartDate = request.StartDate;
				userPlan.WeightGoal = request.WeightGoal;
				userPlan.WeightGoal = request.WeightGoal;
				userPlan.ActivityLevelID = request.ActivityLevelID;
			}

			return userPlan;
		}

		public async Task<UserPlan> Delete(UserPlan userPlan)
		{
			_context.UserPlan.Remove(userPlan);
			await _context.SaveChangesAsync();
			return userPlan;
		}
	}
}
