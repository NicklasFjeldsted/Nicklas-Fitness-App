using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace FitnessWebApi._Controllers
{
	[Route("api/[controller]")]
	[ApiController]
	public class UserMealController : ControllerBase
	{
		private readonly IUserMealService _service;

		public UserMealController(IUserMealService service)
		{
			_service = service;
		}

		[HttpGet("{userId}")]
		public async Task<IActionResult> GetAllById(int userId)
		{
			try
			{
				List<StaticUserMealResponse> meals = await _service.GetAllById(userId);
				if (meals == null)
				{
					return NotFound();
				}

				if(meals.Count == 0)
				{
					return NoContent();
				}

				return Ok(meals);
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
				DirectUserMealResponse meal = await _service.GetById(id);
				if (meal == null)
				{
					return NotFound();
				}

				return Ok(meal);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPost]
		public async Task<IActionResult> Create([FromBody] UserMealRequest request)
		{
			try
			{
				DirectUserMealResponse meal = await _service.Create(request);
				if (meal == null)
				{
					return NotFound();
				}

				return Ok(meal);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPut("{id}")]
		public async Task<IActionResult> Update(int id, [FromBody] UserMealRequest request)
		{
			try
			{
				DirectUserMealResponse meal = await _service.Update(id, request);
				if (meal == null)
				{
					return NotFound();
				}

				return Ok(meal);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpDelete("{id}")]
		public async Task<IActionResult> Delete(int id)
		{
			try
			{
				DirectUserMealResponse meal = await _service.Delete(id);
				if (meal == null)
				{
					return NotFound();
				}

				return Ok(meal);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}
	}
}
