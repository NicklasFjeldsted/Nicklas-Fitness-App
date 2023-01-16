namespace FitnessWebApi._Controllers
{
	[Route("api/[controller]")]
	[ApiController]
	public class UserController : ControllerBase
	{
		private readonly IUserService _service;

		public UserController(IUserService service)
		{
			_service = service;
		}

		[HttpGet]
		public async Task<IActionResult> GetAll()
		{
			try
			{
				List<StaticUserResponse> users = await _service.GetAll();
				if(users == null)
				{
					return Problem("Nothing was returned from service, this was unexpected");
				}

				if(users.Count == 0)
				{
					return NoContent();
				}


				return Ok(users);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpGet("{id}")]
		public async Task<IActionResult> GetById(int id)
		{
			try
			{
				DirectUserResponse user = await _service.GetById(id);
				if (user == null)
				{
					return NotFound();
				}

				return Ok(user);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPost]
		public async Task<IActionResult> Create([FromBody] UserRequest request)
		{
			try
			{
				DirectUserResponse user = await _service.Create(request);
				if (user == null)
				{
					return BadRequest();
				}

				return Ok(user);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPut("{id}")]
		public async Task<IActionResult> Update(int id, [FromBody] UserRequest request)
		{
			try
			{
				DirectUserResponse user = await _service.Update(id, request);
				if (user == null)
				{
					return BadRequest();
				}

				return Ok(user);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}
	}
}
