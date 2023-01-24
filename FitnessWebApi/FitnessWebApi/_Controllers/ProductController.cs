namespace FitnessWebApi._Controllers
{
	[Route("api/[controller]")]
	[ApiController]
	public class ProductController : ControllerBase
	{
		private readonly IProductService _service;

		public ProductController(IProductService service)
		{
			_service = service;
		}

		[HttpGet]
		public async Task<IActionResult> GetAll()
		{
			try
			{
				List<StaticProductResponse> products = await _service.GetAll();
				if (products == null)
				{
					return Problem("Nothing was returned from service, this was unexpected");
				}

				if (products.Count == 0)
				{
					return NoContent();
				}


				return Ok(products);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpGet("{barCode}")]
		public async Task<IActionResult> GetById(string barCode)
		{
			try
			{
				StaticProductResponse product = await _service.GetByBarCode(barCode);
				if (product == null)
				{
					return NotFound();
				}

				return Ok(product);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPost]
		public async Task<IActionResult> Create([FromBody] ProductRequest request)
		{
			try
			{
				StaticProductResponse product = await _service.Create(request);
				if (product == null)
				{
					return BadRequest();
				}

				return Ok(product);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpPut("{barCode}")]
		public async Task<IActionResult> Update(string barCode, [FromBody] ProductRequest request)
		{
			try
			{
				StaticProductResponse product = await _service.Update(barCode, request);
				if (product == null)
				{
					return BadRequest();
				}

				return Ok(product);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}

		[HttpDelete("{barCode}")]
		public async Task<IActionResult> Delete(string barCode)
		{
			try
			{
				StaticProductResponse product = await _service.Delete(barCode);
				if (product == null)
				{
					return BadRequest();
				}

				return Ok(product);
			}
			catch (Exception ex)
			{
				return Problem(ex.Message);
			}
		}
	}
}
